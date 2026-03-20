/*
  Language switching script for the setting page
  Use ChatGPT to explain how to implement language change function (line 6- 20)
*/


/* Set the saved language and update it when the user selects a new one */
document.addEventListener("DOMContentLoaded", () => {
  const languageSelect = document.getElementById("languageSelect");
  const currentLanguage = getSavedLanguage();

  if (languageSelect) {
    languageSelect.value = currentLanguage;

    /* Update language when selection changes */
    languageSelect.addEventListener("change", function () {
      changeLanguage(this.value);
    });
  }
});