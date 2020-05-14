function changeVerifyCode(img) {
    img.src = "/KaptchaServlet?"+Math.floor(Math.random()*100);
}