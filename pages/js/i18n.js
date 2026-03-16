/*
  Language switching script for the website.
  Stores the selected language in localStorage
  and updates page text using data-i18n attributes.
*/

const translations = {
  en: {
    // nav
    back: "Back",

    // home page
    home_title: "Welcome",
    home_subtitle: "Start your fitness journey",
    login: "Login",
    register: "Register",

    // login page
    login_title: "Login",
    email: "Email",
    password: "Password",
    forgot_password: "Forgot password?",
    login_button: "Login",

    // register page
    register_title: "Register",
    username: "Username",
    register_button: "Create account",

    // setting page
    settings_title: "Settings",
    language: "Language",
    choose_language: "Choose your language",
    dark_mode: "Dark Mode",
    save: "Save"
  },

  zh: {
    // nav
    back: "返回",
    logo: "LOGO",
    profile: "个人中心",

    // home page
    home_title: "欢迎",
    home_subtitle: "开始你的健身之旅",
    login: "登录",
    register: "注册",

    // login page
    login_title: "登录",
    email: "邮箱",
    password: "密码",
    forgot_password: "忘记密码？",
    login_button: "登录",

    // register page
    register_title: "注册",
    username: "用户名",
    register_button: "创建账户",

    // setting page
    settings_title: "设置",
    language: "语言",
    choose_language: "请选择你的语言",
    dark_mode: "深色模式",
    save: "保存"
  }
};

/* Use ChatGPT to explian different language change and language memory function (line 73-108) */

/* Get saved language from localStorage */
function getSavedLanguage() {
  return localStorage.getItem("language") || "en";
}

/* Save selected language to localStorage */
function saveLanguage(lang) {
  localStorage.setItem("language", lang);
}

/* Apply translations to all elements with data-i18n */
function applyLanguage(lang) {
  const elements = document.querySelectorAll("[data-i18n]");

  elements.forEach(element => {
    const key = element.getAttribute("data-i18n");
    if (translations[lang] && translations[lang][key]) {
      element.textContent = translations[lang][key];
    }
  });

  document.documentElement.lang = lang;
}

/* Change language and update the page */
function changeLanguage(lang) {
  saveLanguage(lang);
  applyLanguage(lang);
}

/* Apply saved language when the page loads */
document.addEventListener("DOMContentLoaded", () => {
  const currentLanguage = getSavedLanguage();
  applyLanguage(currentLanguage);
});
