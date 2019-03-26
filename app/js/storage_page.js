// To remember previous page when user click 'tips'

let currentPath = window.location.pathname;
let currentPage = currentPath.substring(currentPath.lastIndexOf('/')+1);

if(currentPage.match('tip')) {
  let lastPage = localStorage.getItem("currentPage");
  document.getElementById("backpage").href = "../" + lastPage;
}

$('.page__footer__content').click(function() {
  localStorage.setItem("currentPage", currentPage); 
})