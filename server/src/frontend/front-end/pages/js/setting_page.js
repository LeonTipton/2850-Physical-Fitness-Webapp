document.addEventListener("DOMContentLoaded", () => {
  const defaults = {
    language: "en",
    units: "km",
    emailNotifications: true,
    workoutReminders: false,
    darkMode: false
  };

  function load(key) {
    const val = localStorage.getItem("settings_" + key);
    if (val === null) return defaults[key];
    if (val === "true") return true;
    if (val === "false") return false;
    return val;
  }

  function save(key, val) {
    localStorage.setItem("settings_" + key, val);
  }

  const languageSelect = document.getElementById("languageSelect");
  const currentLanguage = typeof getSavedLanguage === "function" ? getSavedLanguage() : load("language");
  if (languageSelect) {
    languageSelect.value = currentLanguage;
    languageSelect.addEventListener("change", function () {
      save("language", this.value);
      if (typeof changeLanguage === "function") changeLanguage(this.value);
    });
  }

  const unitsSelect = document.getElementById("unitsSelect");
  if (unitsSelect) {
    unitsSelect.value = load("units");
    unitsSelect.addEventListener("change", function () {
      save("units", this.value);
    });
  }

  function initToggle(id, key) {
    const el = document.getElementById(id);
    if (!el) return;
    el.checked = load(key);
    el.addEventListener("change", function () {
      save(key, this.checked);
      if (key === "darkMode") applyDarkMode(this.checked);
    });
  }

  initToggle("toggleEmail", "emailNotifications");
  initToggle("toggleReminders", "workoutReminders");
  initToggle("toggleDarkMode", "darkMode");

  function applyDarkMode(on) {
    document.body.classList.toggle("dark-mode", on);
  }

  applyDarkMode(load("darkMode"));
});

function changePassword() {
  var oldPw = prompt("Enter current password:");
  if (!oldPw) return;
  var newPw = prompt("Enter new password:");
  if (!newPw) return;

  var user = JSON.parse(localStorage.getItem("user") || "{}");
  if (!user.id) { alert("Not logged in"); return; }

  var body = new URLSearchParams();
  body.append("uid", user.id);
  body.append("oldPassword", oldPw);
  body.append("newPassword", newPw);

  fetch("/api/user/password", { method: "POST", body: body })
    .then(function(r) { return r.json(); })
    .then(function(data) {
      alert(data.message || (data.ok ? "Password updated" : "Failed"));
    })
    .catch(function() { alert("Could not connect to server"); });
}

function exportData() {
  const data = {
    exportedAt: new Date().toISOString(),
    workouts: [
      { date: "2026-03-20", type: "Run", duration: "30 min", distance: "5 km" },
      { date: "2026-03-18", type: "Swim", duration: "45 min", distance: "1.5 km" },
      { date: "2026-03-15", type: "Cycle", duration: "60 min", distance: "20 km" }
    ]
  };
  const blob = new Blob([JSON.stringify(data, null, 2)], { type: "application/json" });
  const a = document.createElement("a");
  a.href = URL.createObjectURL(blob);
  a.download = "fitness-data.json";
  a.click();
  URL.revokeObjectURL(a.href);
}

function deleteAccount() {
  if (!confirm("Are you sure you want to delete your account? This cannot be undone.")) return;

  var user = JSON.parse(localStorage.getItem("user") || "{}");
  if (!user.id) {
    localStorage.clear();
    window.location.href = "../index.html";
    return;
  }

  var body = new URLSearchParams();
  body.append("uid", user.id);

  fetch("/api/user/delete", { method: "POST", body: body })
    .then(function(r) { return r.json(); })
    .then(function(data) {
      if (data.ok) {
        localStorage.clear();
        sessionStorage.clear();
        window.location.href = "../index.html";
      } else {
        alert(data.message || "Delete failed");
      }
    })
    .catch(function() { alert("Could not connect to server"); });
}
