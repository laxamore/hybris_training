# SAP Commerce / Hybris Training

## Note

### Create Custom Search Populator

```

Add attribute sku & material in trainingcore-items.xmlAdd sku & material to ProductData in trainingfacades-beans.xmlAnt clean all & update systemCreate TrainingSearchResultProductPopulator.java in custom/training/trainingfacades/src/org/training/facades/populators/TrainingSearchResultProductPopulator.javaAdd sku & material to indexedProperties in solr.impexant all & start serverExecute impex script
Using the populator

Register populator in trainingfacades-spring.xml
<alias name="defaultTrainingSearchResultProductPopulator" alias="trainingSearchResultProductPopulator"/>

<bean id="defaultTrainingSearchResultProductPopulator" class="org.training.facades.populators.TrainingSearchResultProductPopulator" parent="defaultCommerceSearchResultProductPopulator">

</bean>



<bean parent="modifyPopulatorList">

    <property name="list" ref="commerceSearchResultProductConverter"/>

    <property name="add" ref="trainingSearchResultProductPopulator"/>

</bean>



ant all & start serverTo check the changes, edit productListerItem.tag, add code below
<div>

     <ycommerce:testId code="searchPage_productName_link_${product.sku}">

        ${ycommerce:sanitizeHTML(product.sku)}</a>

     </ycommerce:testId>

   </div>



   <div>

     <ycommerce:testId code="searchPage_productName_link_${product.material}">

        ${ycommerce:sanitizeHTML(product.material)}</a>

     </ycommerce:testId>

   </div>
```

## Task

### Task 28-01-2022

```
Tugas 28/01/2022
1. Tambah attr 'status' di Supplier dg tipe enum
2. Enum berisi : VERIFIED, IN_REVIEW, NOT_VERIFIED
3. Buat cronjob utk menampilkan list supplier di log sekaligus mengubah status supplier dari NOT_VERIFIED -> IN_REVIEW
4. Cronjob running setiap jam 12 malam setiap hari

Kirim dalam bentuk .zip
1. trainingcore-items.xml
2. Screenshot log ketika cronjob dijalankan
3. Hasil perubahan status utk salah satu data Supplier di Backoffice

Hint:
1. Gunakan DAO utk get list Supplier
2. Gunakan modelService utk menyimpan perubahan status
```

### Task 31-01-2022

```
Tugas Solr 31/01/2022

Tambah attribute ‘Color’ ke TrainingVariantProductIsi data color utk semua productIndexing color ke dalam solr agar datanya bisa ditampilkan di PLP dan di filter PLPTampilkan informasi color setelah info material

Dikumpulkan paling lambat hari ini pkl 18.00 WIB, yg dikumpulkan:

Screenshot property yg sudah terindex di dashboard solrScreenshot PLP

Optional Task:

Tambah attribute ‘Rating’ ke TrainingVariantProductIsi data rating utk semua product(range rating 0-5)Buat custom provider utk mengindex rating sehingga di PLP akan muncul info jika rating di atas 4 maka akan menjadi product ‘Best Seller’Munculkan info Best Seller ini di lister grid item product
```
### Task 02-02-2022

```
Tugas materi workflow & business process
1. Buat workflow untuk set customer.description
2. Jika approve set dengan string 'approve'
3. Jika reject set dengan string 'reject'
4. Setelah set description langsung trigger business process dengan 3 action & membawa value string approve/reject
5. Di action 1 print di log "Test Action 1 OK : "+reject/approve
6. Jika approve lanjut ke action ke 2
7. Jika reject langsung ke action ke 3
8. Di action 2 print di log "Test Action 2 OK" & return OK to success
9. Di action 3 print di log "Test Action 3 OK" & return NOK to error
```
