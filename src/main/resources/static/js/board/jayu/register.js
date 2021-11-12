$(document).ready(function() {
  const categoryArr = ["소곤소곤","이직","JOB담","연애","결혼·육아","재태크","취미","뷰티·패션","스포츠·게임"];
  for(var i = 0; i < categoryArr.length; i++){
      $(".category").append("<option value="+categoryArr[i]+">" + categoryArr[i] + "</option>");
  }
});
