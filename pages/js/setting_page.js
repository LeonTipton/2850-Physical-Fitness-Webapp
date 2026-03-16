document.addEventListener("DOMContentLoaded", () => {
  const languageSelect = document.getElementById("languageSelect");
  const currentLanguage = getSavedLanguage();

  if (languageSelect) {
    languageSelect.value = currentLanguage;

    languageSelect.addEventListener("change", function () {
      changeLanguage(this.value);
    });
  }
});