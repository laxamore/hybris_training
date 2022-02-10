$("#nextBtn").click(async () => {
  let response = await fetch(
    "https://localhost:9002/trainingstorefront/training/id/custom-page/trainingCustomPage/next"
  );
  let responseJSON = await response.json();
  $(".item img").attr('src', `${responseJSON.images[1].url}`);
  $(".name").text(responseJSON.name);
  $(".code").text(responseJSON.code);
  $(".price").text(responseJSON.price.formattedValue);
  console.log(responseJSON);
});

$("#prevBtn").click(async () => {
  let response = await fetch(
    "https://localhost:9002/trainingstorefront/training/id/custom-page/trainingCustomPage/prev"
  );
  let responseJSON = await response.json();
  $(".item img").attr('src', `${responseJSON.images[1].url}`);
  $(".name").text(responseJSON.name);
  $(".code").text(responseJSON.code);
  $(".price").text(responseJSON.price.formattedValue);
  console.log(responseJSON);
});
