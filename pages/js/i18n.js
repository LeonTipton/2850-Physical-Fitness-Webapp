/*
  Language switching script for the website.
  Stores the selected language in localStorage
  and updates page text using data-i18n attributes.
*/

const translations = {
  en: {
    // nav
    back: "Back",

    // setting page
    settings_title: "Settings",
    language: "Language",
    choose_language: "Choose your language",

    // swim page
    swim_title: "Swim Activity",
    swim_subtitle: "Track your swimming and progress",
    new_swim: "New Swim",
    this_week: "This week",
    distance: "Distance (m)",
    duration: "Duration (min)",
    calories_burned: "Calories burned",
    laps: "Laps",
    pool_length: "Pool Length (m)",
    select_length: "Select length",
    avg_pace: "Avg. pace (min/100m)",
    stroke_type: "Stroke Type",
    select_stroke: "Select stroke",
    freestyle: "Freestyle",
    backstroke: "Backstroke",
    breaststroke: "Breaststroke",
    butterfly: "Butterfly",
    individual_medley: "Individual Medley",
    location: "Location",
    loaction_placeholder: "Pool or facility name",
    notes: "Notes",
    swim_notes_placeholder: "Add notes about your swim...",
    start_swim: "Start Swim",
    pause: "Pause",
    finish: "Finish",
    save_swim: "Save Swim",
    your_stats: "Your Stats",
    total_swim: "Total Swims",
    total_distance: "Total Distance",
    avgpace: "Avg Pace (min/100m)",
    longest_swim: "Longest Swim",
    recent_swim: "Recent Swims",
  },

  zh: {
    // nav
    back: "返回",

    // setting page
    settings_title: "设置",
    language: "语言",
    choose_language: "请选择你的语言",
    dark_mode: "深色模式",
    save: "保存",

    // swim page
    swim_title: "游泳活动",
    swim_subtitle: "记录你的游泳情况和进度",
    new_swim: "新建游泳记录",
    this_week: "本周",
    distance: "距离（米）",
    duration: "时长（分钟）",
    calories_burned: "消耗卡路里",
    laps: "趟数",
    pool_length: "泳池长度（米）",
    select_length: "选择长度",
    avg_pace: "平均配速（分钟/100米）",
    stroke_type: "泳姿",
    select_stroke: "选择泳姿",
    freestyle: "自由泳",
    backstroke: "仰泳",
    breaststroke: "蛙泳",
    butterfly: "蝶泳",
    individual_medley: "个人混合泳",
    location: "地点",
    loaction_placeholder: "泳池或场馆名称",
    notes: "备注",
    swim_notes_placeholder: "添加你的游泳备注……",
    start_swim: "开始游泳",
    pause: "暂停",
    finish: "结束",
    save_swim: "保存游泳记录",
    your_stats: "你的统计",
    total_swim: "总游泳次数",
    total_distance: "总距离",
    avgpace: "平均配速（分钟/100米）",
    longest_swim: "最长游泳距离",
    recent_swim: "最近游泳记录",
  },

  es: {
    // nav
    back: "Atrás",

    // setting page
    settings_title: "Configuración",
    language: "Idioma",
    choose_language: "Elige tu idioma",
    dark_mode: "Modo oscuro",
    save: "Guardar",

    // swim page
    swim_title: "Actividad de natación",
    swim_subtitle: "Registra tu natación y tu progreso",
    new_swim: "Nueva natación",
    this_week: "Esta semana",
    distance: "Distancia (m)",
    duration: "Duración (min)",
    calories_burned: "Calorías quemadas",
    laps: "Largos",
    pool_length: "Longitud de la piscina (m)",
    select_length: "Seleccionar longitud",
    avg_pace: "Ritmo medio (min/100 m)",
    stroke_type: "Estilo de nado",
    select_stroke: "Seleccionar estilo",
    freestyle: "Crol",
    backstroke: "Espalda",
    breaststroke: "Braza",
    butterfly: "Mariposa",
    individual_medley: "Estilos individual",
    location: "Ubicación",
    loaction_placeholder: "Nombre de la piscina o instalación",
    notes: "Notas",
    swim_notes_placeholder: "Añade notas sobre tu sesión de natación...",
    start_swim: "Comenzar natación",
    pause: "Pausa",
    finish: "Finalizar",
    save_swim: "Guardar natación",
    your_stats: "Tus estadísticas",
    total_swim: "Total de sesiones",
    total_distance: "Distancia total",
    avgpace: "Ritmo medio (min/100 m)",
    longest_swim: "Sesión más larga",
    recent_swim: "Sesiones recientes",
  },

  fr: {
    // nav
    back: "Retour",

    // setting page
    settings_title: "Paramètres",
    language: "Langue",
    choose_language: "Choisissez votre langue",
    dark_mode: "Mode sombre",
    save: "Enregistrer",

    // swim page
    swim_title: "Activité de natation",
    swim_subtitle: "Suivez vos séances de natation et votre progression",
    new_swim: "Nouvelle séance",
    this_week: "Cette semaine",
    distance: "Distance (m)",
    duration: "Durée (min)",
    calories_burned: "Calories brûlées",
    laps: "Longueurs",
    pool_length: "Longueur du bassin (m)",
    select_length: "Sélectionner une longueur",
    avg_pace: "Allure moyenne (min/100 m)",
    stroke_type: "Type de nage",
    select_stroke: "Sélectionner une nage",
    freestyle: "Nage libre",
    backstroke: "Dos crawlé",
    breaststroke: "Brasse",
    butterfly: "Papillon",
    individual_medley: "4 nages",
    location: "Lieu",
    loaction_placeholder: "Nom de la piscine ou de l'établissement",
    notes: "Notes",
    swim_notes_placeholder: "Ajoutez des notes sur votre séance de natation...",
    start_swim: "Commencer",
    pause: "Pause",
    finish: "Terminer",
    save_swim: "Enregistrer la séance",
    your_stats: "Vos statistiques",
    total_swim: "Nombre total de séances",
    total_distance: "Distance totale",
    avgpace: "Allure moyenne (min/100 m)",
    longest_swim: "Plus longue séance",
    recent_swim: "Séances récentes",
  },

  ja: {
    // nav
    back: "戻る",

    // setting page
    settings_title: "設定",
    language: "言語",
    choose_language: "言語を選択してください",
    dark_mode: "ダークモード",
    save: "保存",

    // swim page
    swim_title: "水泳アクティビティ",
    swim_subtitle: "水泳の記録と進捗を管理しましょう",
    new_swim: "新しい水泳記録",
    this_week: "今週",
    distance: "距離 (m)",
    duration: "時間 (分)",
    calories_burned: "消費カロリー",
    laps: "ラップ数",
    pool_length: "プールの長さ (m)",
    select_length: "長さを選択",
    avg_pace: "平均ペース (分/100m)",
    stroke_type: "泳法",
    select_stroke: "泳法を選択",
    freestyle: "自由形",
    backstroke: "背泳ぎ",
    breaststroke: "平泳ぎ",
    butterfly: "バタフライ",
    individual_medley: "個人メドレー",
    location: "場所",
    loaction_placeholder: "プールまたは施設名",
    notes: "メモ",
    swim_notes_placeholder: "水泳についてのメモを追加...",
    start_swim: "開始",
    pause: "一時停止",
    finish: "終了",
    save_swim: "記録を保存",
    your_stats: "あなたの統計",
    total_swim: "総水泳回数",
    total_distance: "総距離",
    avgpace: "平均ペース (分/100m)",
    longest_swim: "最長距離",
    recent_swim: "最近の水泳記録",
  },

  ko: {
    // nav
    back: "뒤로",

    // setting page
    settings_title: "설정",
    language: "언어",
    choose_language: "언어를 선택하세요",
    dark_mode: "다크 모드",
    save: "저장",

    // swim page
    swim_title: "수영 활동",
    swim_subtitle: "수영 기록과 진행 상황을 추적하세요",
    new_swim: "새 수영 기록",
    this_week: "이번 주",
    distance: "거리 (m)",
    duration: "시간 (분)",
    calories_burned: "소모 칼로리",
    laps: "랩 수",
    pool_length: "수영장 길이 (m)",
    select_length: "길이 선택",
    avg_pace: "평균 페이스 (분/100m)",
    stroke_type: "영법",
    select_stroke: "영법 선택",
    freestyle: "자유형",
    backstroke: "배영",
    breaststroke: "평영",
    butterfly: "접영",
    individual_medley: "개인혼영",
    location: "위치",
    loaction_placeholder: "수영장 또는 시설 이름",
    notes: "메모",
    swim_notes_placeholder: "수영에 대한 메모를 추가하세요...",
    start_swim: "수영 시작",
    pause: "일시정지",
    finish: "종료",
    save_swim: "수영 기록 저장",
    your_stats: "내 통계",
    total_swim: "총 수영 횟수",
    total_distance: "총 거리",
    avgpace: "평균 페이스 (분/100m)",
    longest_swim: "가장 긴 수영 거리",
    recent_swim: "최근 수영 기록",
  }


};

/* Use ChatGPT to explian different language change and language memory function (line -) */

/* Get the saved language from localStorage, or use English by default */
function getSavedLanguage() {
  return localStorage.getItem("language") || "en";
}

/* Save the selected language to localStorage */
function saveLanguage(lang) {
  localStorage.setItem("language", lang);
}

/* Apply translated text, placeholders, and page title */
function applyLanguage(lang) {
  const safeLang = translations[lang] ? lang : "en";

  /* Update normal text content */
  const elements = document.querySelectorAll("[data-i18n]");
  elements.forEach(element => {
    const key = element.getAttribute("data-i18n");
    if (translations[safeLang][key]) {
      element.textContent = translations[safeLang][key];
    }
  });

  /* Update placeholder text for input fields */
  const placeholderElements = document.querySelectorAll("[data-i18n-placeholder]");
  placeholderElements.forEach(element => {
    const key = element.getAttribute("data-i18n-placeholder");
    if (translations[safeLang][key]) {
      element.placeholder = translations[safeLang][key];
    }
  });

  /* Update the page title */
  const titleElement = document.querySelector("title[data-i18n]");
  if (titleElement) {
    const key = titleElement.getAttribute("data-i18n");
    if (translations[safeLang][key]) {
      document.title = translations[safeLang][key];
    }
  }

  /* Update the HTML language attribute */
  document.documentElement.lang = safeLang;
}

/* Save and apply the new language */
function changeLanguage(lang) {
  saveLanguage(lang);
  applyLanguage(lang);
}

/* Apply the saved language when the page loads */
document.addEventListener("DOMContentLoaded", () => {
  const currentLanguage = getSavedLanguage();
  applyLanguage(currentLanguage);
});
