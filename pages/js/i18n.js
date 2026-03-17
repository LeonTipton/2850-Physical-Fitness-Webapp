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
    strp_subtitle: "Select a muscle group to view exercises",

    // strength page
    strp_title: "Choose Strength Exercise",
    strp_subtitle: "Select a muscle group to view exercises",
    strp_chest: "Chest",
    strp_back: "Back",
    strp_legs: "Legs",
    strp_shoulders: "Shoulders",
    strp_arms: "Arms",
    strp_core: "Core",
    strp_back_btn: "← Back to categories",
    strp_select_exercise: "Select an exercise",
    ex_bench_press: "Bench Press",
    ex_incline_bench_press: "Incline Bench Press",
    ex_chest_press_machine: "Chest Press Machine",
    ex_push_up: "Push Up",
    ex_lat_pulldown: "Lat Pulldown",
    ex_seated_row: "Seated Row",
    ex_pull_up: "Pull Up",
    ex_deadlift: "Deadlift",
    ex_squat: "Squat",
    ex_leg_press: "Leg Press",
    ex_leg_extension: "Leg Extension",
    ex_leg_curl: "Leg Curl",
    ex_shoulder_press: "Shoulder Press",
    ex_lateral_raise: "Lateral Raise",
    ex_front_raise: "Front Raise",
    ex_bicep_curl: "Bicep Curl",
    ex_hammer_curl: "Hammer Curl",
    ex_tricep_pushdown: "Tricep Pushdown",
    ex_tricep_dip: "Tricep Dip",
    ex_plank: "Plank",
    ex_crunch: "Crunch",
    ex_russian_twist: "Russian Twist",
    ex_leg_raise: "Leg Raise",

    // strength workout page
    swp_title: "Strength Workout",
    swp_subtitle: "Record your exercise",
    swp_card_title: "New Workout",
    swp_t_w: "This week",
    swp_sessions: "sessions",
    swp_exer_name: "Exercise Name (optional)",
    swp_placeholder_e_g: "e.g. Bench Press, Plank, Squat",
    swp_sets: "Sets",
    swp_reps: "Reps",
    swp_weight: "Weight (kg)",
    swp_duration: "Duration (min)",
    swp_notes: "Notes",
    swp_placeholder_note: "Add notes about your workout...",
    swp_back_btn: "Back",
    swp_save_workout_btn: "Save Workout",
    swp_your_stats: "Your Stats",
    swp_t_m: "This Month",
    swp_total_exer: "Total Exercises",
    swp_max_bench: "Max Bench",
    swp_max_squat: "Max Squat",
    swp_recent_workout: "Recent Workouts",

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
    recent_swim: "Recent Swims"
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

    // strength page 
		strp_title: "选择力量训练动作",
		strp_subtitle: "选择一个肌肉群以查看动作",
		strp_chest: "胸部",
		strp_back: "背部",
		strp_legs: "腿部",
		strp_shoulders: "肩部",
		strp_arms: "手臂",
		strp_core: "核心",
		strp_back_btn: "← 返回分类",
    strp_select_exercise: "选择一个动作",
		ex_bench_press: "卧推",
		ex_incline_bench_press: "上斜卧推",
		ex_chest_press_machine: "器械推胸",
		ex_push_up: "俯卧撑",
		ex_lat_pulldown: "高位下拉",
		ex_seated_row: "坐姿划船",
		ex_pull_up: "引体向上",
		ex_deadlift: "硬拉",
		ex_squat: "深蹲",
		ex_leg_press: "腿举",
		ex_leg_extension: "腿屈伸",
		ex_leg_curl: "腿弯举",
		ex_shoulder_press: "肩推",
		ex_lateral_raise: "侧平举",
		ex_front_raise: "前平举",
		ex_bicep_curl: "二头弯举",
		ex_hammer_curl: "锤式弯举",
		ex_tricep_pushdown: "三头下压",
		ex_tricep_dip: "双杠臂屈伸",
		ex_plank: "平板支撑",
		ex_crunch: "卷腹",
		ex_russian_twist: "俄罗斯转体",
		ex_leg_raise: "抬腿",

    // strength workout page
    swp_title: "力量训练",
    swp_subtitle: "记录你的锻炼",
    swp_card_title: "新建训练",
    swp_t_w: "本周",
    swp_sessions: "次训练",
    swp_exer_name: "运动名称（可选）",
    swp_placeholder_e_g: "例如：卧推、平板支撑、深蹲",
    swp_sets: "组数",
    swp_reps: "次数",
    swp_weight: "重量（kg）",
    swp_duration: "时长（分钟）",
    swp_notes: "备注",
    swp_placeholder_note: "添加关于本次训练的备注……",
    swp_back_btn: "返回",
    swp_save_workout_btn: "保存训练",
    swp_your_stats: "你的统计",
    swp_t_m: "本月",
    swp_total_exer: "总训练动作数",
    swp_max_bench: "卧推最大重量",
    swp_max_squat: "深蹲最大重量",
    swp_recent_workout: "最近训练记录",

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

		// strength page 
		strp_title: "Elegir ejercicio de fuerza",
		strp_subtitle: "Selecciona un grupo muscular para ver los ejercicios",
		strp_chest: "Pecho",
		strp_back: "Espalda",
		strp_legs: "Piernas",
		strp_shoulders: "Hombros",
		strp_arms: "Brazos",
		strp_core: "Core",
		strp_back_btn: "← Volver a categorías",
		ex_bench_press: "Press de banca",
		ex_incline_bench_press: "Press de banca inclinado",
		ex_chest_press_machine: "Máquina de press de pecho",
		ex_push_up: "Flexiones",
		ex_lat_pulldown: "Jalón al pecho",
		ex_seated_row: "Remo sentado",
		ex_pull_up: "Dominadas",
		ex_deadlift: "Peso muerto",
		ex_squat: "Sentadilla",
		ex_leg_press: "Prensa de piernas",
		ex_leg_extension: "Extensión de piernas",
		ex_leg_curl: "Curl de piernas",
		ex_shoulder_press: "Press de hombros",
		ex_lateral_raise: "Elevación lateral",
		ex_front_raise: "Elevación frontal",
		ex_bicep_curl: "Curl de bíceps",
		ex_hammer_curl: "Curl martillo",
		ex_tricep_pushdown: "Extensión de tríceps en polea",
		ex_tricep_dip: "Fondos de tríceps",
		ex_plank: "Plancha",
		ex_crunch: "Crunch",
		ex_russian_twist: "Giro ruso",
		ex_leg_raise: "Elevación de piernas",
    strp_select_exercise: "Selecciona un ejercicio",

    // strength workout page
    swp_title: "Entrenamiento de fuerza",
    swp_subtitle: "Registra tu ejercicio",
    swp_card_title: "Nuevo entrenamiento",
    swp_t_w: "Esta semana",
    swp_sessions: "sesiones",
    swp_exer_name: "Nombre del ejercicio (opcional)",
    swp_placeholder_e_g: "p. ej. Press de banca, plancha, sentadilla",
    swp_sets: "Series",
    swp_reps: "Repeticiones",
    swp_weight: "Peso (kg)",
    swp_duration: "Duración (min)",
    swp_notes: "Notas",
    swp_placeholder_note: "Añade notas sobre tu entrenamiento...",
    swp_back_btn: "Atrás",
    swp_save_workout_btn: "Guardar entrenamiento",
    swp_your_stats: "Tus estadísticas",
    swp_t_m: "Este mes",
    swp_total_exer: "Total de ejercicios",
    swp_max_bench: "Press de banca máximo",
    swp_max_squat: "Sentadilla máxima",
    swp_recent_workout: "Entrenamientos recientes",

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
    recent_swim: "Sesiones recientes"
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

		// strength page 
		strp_title: "Choisir un exercice de musculation",
		strp_subtitle: "Sélectionnez un groupe musculaire pour voir les exercices",
		strp_chest: "Poitrine",
		strp_back: "Dos",
		strp_legs: "Jambes",
		strp_shoulders: "Épaules",
		strp_arms: "Bras",
		strp_core: "Abdominaux",
		strp_back_btn: "← Retour aux catégories",
    strp_select_exercise: "Sélectionnez un exercice",
		ex_bench_press: "Développé couché",
		ex_incline_bench_press: "Développé couché incliné",
		ex_chest_press_machine: "Machine de développé poitrine",
		ex_push_up: "Pompes",
		ex_lat_pulldown: "Tirage vertical",
		ex_seated_row: "Rowing assis",
		ex_pull_up: "Traction",
		ex_deadlift: "Soulevé de terre",
		ex_squat: "Squat",
		ex_leg_press: "Presse à jambes",
		ex_leg_extension: "Extension des jambes",
		ex_leg_curl: "Leg curl",
		ex_shoulder_press: "Développé épaules",
		ex_lateral_raise: "Élévation latérale",
		ex_front_raise: "Élévation frontale",
		ex_bicep_curl: "Curl biceps",
		ex_hammer_curl: "Curl marteau",
		ex_tricep_pushdown: "Extension triceps à la poulie",
		ex_tricep_dip: "Dips triceps",
		ex_plank: "Planche",
		ex_crunch: "Crunch",
		ex_russian_twist: "Twist russe",
		ex_leg_raise: "Relevé de jambes",

    // strength workout page
    swp_title: "Entraînement de musculation",
    swp_subtitle: "Enregistrez votre exercice",
    swp_card_title: "Nouvel entraînement",
    swp_t_w: "Cette semaine",
    swp_sessions: "séances",
    swp_exer_name: "Nom de l'exercice (optionnel)",
    swp_placeholder_e_g: "ex. développé couché, gainage, squat",
    swp_sets: "Séries",
    swp_reps: "Répétitions",
    swp_weight: "Poids (kg)",
    swp_duration: "Durée (min)",
    swp_notes: "Notes",
    swp_placeholder_note: "Ajoutez des notes sur votre entraînement...",
    swp_back_btn: "Retour",
    swp_save_workout_btn: "Enregistrer l'entraînement",
    swp_your_stats: "Vos statistiques",
    swp_t_m: "Ce mois-ci",
    swp_total_exer: "Total des exercices",
    swp_max_bench: "Développé couché max",
    swp_max_squat: "Squat max",
    swp_recent_workout: "Entraînements récents",

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
    recent_swim: "Séances récentes"
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

		// strength page 
		strp_title: "筋力トレーニング種目を選択",
		strp_subtitle: "筋肉グループを選択して種目を表示します",
		strp_chest: "胸",
		strp_back: "背中",
		strp_legs: "脚",
		strp_shoulders: "肩",
		strp_arms: "腕",
		strp_core: "体幹",
		strp_back_btn: "← カテゴリーに戻る",
    strp_select_exercise: "種目を選択",
		ex_bench_press: "ベンチプレス",
		ex_incline_bench_press: "インクラインベンチプレス",
		ex_chest_press_machine: "チェストプレスマシン",
		ex_push_up: "腕立て伏せ",
		ex_lat_pulldown: "ラットプルダウン",
		ex_seated_row: "シーテッドロー",
		ex_pull_up: "懸垂",
		ex_deadlift: "デッドリフト",
		ex_squat: "スクワット",
		ex_leg_press: "レッグプレス",
		ex_leg_extension: "レッグエクステンション",
		ex_leg_curl: "レッグカール",
		ex_shoulder_press: "ショルダープレス",
		ex_lateral_raise: "サイドレイズ",
		ex_front_raise: "フロントレイズ",
		ex_bicep_curl: "バイセップカール",
		ex_hammer_curl: "ハンマーカール",
		ex_tricep_pushdown: "トライセッププッシュダウン",
		ex_tricep_dip: "トライセップディップ",
		ex_plank: "プランク",
		ex_crunch: "クランチ",
		ex_russian_twist: "ロシアンツイスト",
		ex_leg_raise: "レッグレイズ",

    // strength workout page
    swp_title: "筋力トレーニング",
    swp_subtitle: "トレーニングを記録しましょう",
    swp_card_title: "新しいトレーニング",
    swp_t_w: "今週",
    swp_sessions: "セッション",
    swp_exer_name: "種目名（任意）",
    swp_placeholder_e_g: "例：ベンチプレス、プランク、スクワット",
    swp_sets: "セット数",
    swp_reps: "回数",
    swp_weight: "重量 (kg)",
    swp_duration: "時間 (分)",
    swp_notes: "メモ",
    swp_placeholder_note: "トレーニングについてのメモを追加...",
    swp_back_btn: "戻る",
    swp_save_workout_btn: "トレーニングを保存",
    swp_your_stats: "あなたの統計",
    swp_t_m: "今月",
    swp_total_exer: "総種目数",
    swp_max_bench: "ベンチプレス最大重量",
    swp_max_squat: "スクワット最大重量",
    swp_recent_workout: "最近のトレーニング",

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
    recent_swim: "最近の水泳記録"
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

		// strength page 
		strp_title: "근력 운동 선택",
		strp_subtitle: "운동을 보려면 근육 부위를 선택하세요",
		strp_chest: "가슴",
		strp_back: "등",
		strp_legs: "다리",
		strp_shoulders: "어깨",
		strp_arms: "팔",
		strp_core: "코어",
		strp_back_btn: "← 카테고리로 돌아가기",
    strp_select_exercise: "운동을 선택하세요",
		ex_bench_press: "벤치프레스",
		ex_incline_bench_press: "인클라인 벤치프레스",
		ex_chest_press_machine: "체스트 프레스 머신",
		ex_push_up: "푸시업",
		ex_lat_pulldown: "랫풀다운",
		ex_seated_row: "시티드 로우",
		ex_pull_up: "풀업",
		ex_deadlift: "데드리프트",
		ex_squat: "스쿼트",
		ex_leg_press: "레그프레스",
		ex_leg_extension: "레그 익스텐션",
		ex_leg_curl: "레그 컬",
		ex_shoulder_press: "숄더 프레스",
		ex_lateral_raise: "사이드 레터럴 레이즈",
		ex_front_raise: "프론트 레이즈",
		ex_bicep_curl: "바이셉 컬",
		ex_hammer_curl: "해머 컬",
		ex_tricep_pushdown: "트라이셉 푸시다운",
		ex_tricep_dip: "트라이셉 딥",
		ex_plank: "플랭크",
		ex_crunch: "크런치",
		ex_russian_twist: "러시안 트위스트",
		ex_leg_raise: "레그 레이즈",

    // strength workout page
    swp_title: "근력 운동",
    swp_subtitle: "운동을 기록하세요",
    swp_card_title: "새 운동",
    swp_t_w: "이번 주",
    swp_sessions: "회",
    swp_exer_name: "운동 이름 (선택 사항)",
    swp_placeholder_e_g: "예: 벤치프레스, 플랭크, 스쿼트",
    swp_sets: "세트",
    swp_reps: "횟수",
    swp_weight: "무게 (kg)",
    swp_duration: "시간 (분)",
    swp_notes: "메모",
    swp_placeholder_note: "운동에 대한 메모를 추가하세요...",
    swp_back_btn: "뒤로",
    swp_save_workout_btn: "운동 저장",
    swp_your_stats: "내 통계",
    swp_t_m: "이번 달",
    swp_total_exer: "총 운동 수",
    swp_max_bench: "최대 벤치프레스",
    swp_max_squat: "최대 스쿼트",
    swp_recent_workout: "최근 운동 기록",

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
    recent_swim: "최근 수영 기록"
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

/* JavaScript dynamic content translation */
function t(key) {
  const safeLang = getCurrentLanguage();
  return translations[safeLang][key] || key;
}
