package org.training.storefront.controllers.pages;

import de.hybris.platform.acceleratorservices.controllers.page.PageType;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.impl.ProductBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.forms.ReviewForm;
import de.hybris.platform.acceleratorstorefrontcommons.util.MetaSanitizerUtil;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commercefacades.order.data.ConfigurationInfoData;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commercefacades.product.data.ImageDataType;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.ProductSearchFacade;
import de.hybris.platform.util.Config;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;


@Controller
@RequestMapping("/custom-page")
public class TrainingCustomPageController extends AbstractPageController {
    private static final String FUTURE_STOCK_ENABLED = "storefront.products.futurestock.enabled";
    private static final String TRAINING_CUSTOM_PAGE = "trainingCustomPage";
    private List<ProductData> listProductData;
    private ProductData currentProductData;

    @Resource(name = "productBreadcrumbBuilder")
    private ProductBreadcrumbBuilder productBreadcrumbBuilder;

    @Resource(name = "productVariantFacade")
    private ProductFacade productFacade;

    @Resource(name = "productSearchFacade")
    private ProductSearchFacade<ProductData> productSearchFacade;

    @RequestMapping(value = TRAINING_CUSTOM_PAGE, method = RequestMethod.GET)
    public String getTrainingCustomPage(final Model model) throws CMSItemNotFoundException {
        final String productCode = "PB-SHIRT-1";

        listProductData = productSearchFacade.categorySearch("FASHION").getResults();

        for (Integer i = 0; i < listProductData.size(); i++) {
            final List<ProductOption> extraOptions = Arrays.asList(ProductOption.VARIANT_MATRIX_BASE, ProductOption.VARIANT_MATRIX_URL,
                    ProductOption.VARIANT_MATRIX_MEDIA, ProductOption.VARIANT_FIRST_VARIANT, ProductOption.BASIC,
                    ProductOption.URL, ProductOption.PRICE, ProductOption.SUMMARY, ProductOption.DESCRIPTION, ProductOption.GALLERY,
                    ProductOption.CATEGORIES, ProductOption.REVIEW, ProductOption.PROMOTIONS, ProductOption.CLASSIFICATION,
                    ProductOption.VARIANT_FULL, ProductOption.STOCK, ProductOption.VOLUME_PRICES, ProductOption.PRICE_RANGE,
                    ProductOption.DELIVERY_MODE_AVAILABILITY);

            listProductData.set(i, productFacade.getProductForCodeAndOptions(listProductData.get(i).getCode(), extraOptions));
        }

        currentProductData = listProductData.get(0);

        updatePageTitle(productCode, model);

        final ContentPageModel trainingCustomPageData = getContentPageForLabelOrId(TRAINING_CUSTOM_PAGE);

        storeCmsPageInModel(model, trainingCustomPageData);
        populateProductData(currentProductData, model);
        model.addAttribute(WebConstants.BREADCRUMBS_KEY, productBreadcrumbBuilder.getBreadcrumbs(productCode));

        model.addAttribute(new ReviewForm());
        model.addAttribute("pageType", PageType.PRODUCT.name());
        model.addAttribute("futureStockEnabled", Boolean.valueOf(Config.getBoolean(FUTURE_STOCK_ENABLED, false)));

        final String metaKeywords = MetaSanitizerUtil.sanitizeKeywords(currentProductData.getKeywords());
        final String metaDescription = MetaSanitizerUtil.sanitizeDescription(currentProductData.getDescription());

        setUpMetaDataForContentPage(model, trainingCustomPageData);
        setUpMetaData(model, metaKeywords, metaDescription);

        return getViewForPage(model);
    }

    @RequestMapping(value = TRAINING_CUSTOM_PAGE + "/next", method = RequestMethod.GET)
    @ResponseBody
    public ProductData getNext() {
        Integer idx = listProductData.indexOf(currentProductData);
        if (listProductData.size() - 1 == idx)
            return currentProductData;
        currentProductData = listProductData.get(idx+1);
        return currentProductData;
    }

    @RequestMapping(value = TRAINING_CUSTOM_PAGE + "/prev", method = RequestMethod.GET)
    @ResponseBody
    public ProductData getPrevious() {
        Integer idx = listProductData.indexOf(currentProductData);
        if (idx == 0)
            return currentProductData;
        currentProductData = listProductData.get(idx-1);
        return currentProductData;
    }

    protected void updatePageTitle(final String productCode, final Model model) {
        storeContentPageTitleInModel(model, getPageTitleResolver().resolveProductPageTitle(productCode));
    }

    protected void populateProductData(final ProductData productData, final Model model) {
        model.addAttribute("galleryImages", getGalleryImages(productData));
        model.addAttribute("product", productData);

        if (productData.getConfigurable()) {
            final List<ConfigurationInfoData> configurations = productFacade.getConfiguratorSettingsForCode(productData.getCode());
            if (CollectionUtils.isNotEmpty(configurations)) {
                model.addAttribute("configuratorType", configurations.get(0).getConfiguratorType());
            }
        }
    }

    protected List<Map<String, ImageData>> getGalleryImages(final ProductData productData) {
        final List<Map<String, ImageData>> galleryImages = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(productData.getImages())) {
            final List<ImageData> images = new ArrayList<>();
            for (final ImageData image : productData.getImages()) {
                if (ImageDataType.GALLERY.equals(image.getImageType())) {
                    images.add(image);
                }
            }
            Collections.sort(images, new Comparator<ImageData>() {
                @Override
                public int compare(final ImageData image1, final ImageData image2) {
                    return image1.getGalleryIndex().compareTo(image2.getGalleryIndex());
                }
            });

            if (CollectionUtils.isNotEmpty(images)) {
                addFormatsToGalleryImages(galleryImages, images);
            }
        }
        return galleryImages;
    }

    protected void addFormatsToGalleryImages(final List<Map<String, ImageData>> galleryImages, final List<ImageData> images) {
        int currentIndex = images.get(0).getGalleryIndex().intValue();
        Map<String, ImageData> formats = new HashMap<String, ImageData>();
        for (final ImageData image : images) {
            if (currentIndex != image.getGalleryIndex().intValue()) {
                galleryImages.add(formats);
                formats = new HashMap<>();
                currentIndex = image.getGalleryIndex().intValue();
            }
            formats.put(image.getFormat(), image);
        }
        if (!formats.isEmpty()) {
            galleryImages.add(formats);
        }
    }
}
