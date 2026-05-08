/**
 * activity-store.js
 * Shared localStorage utility for all activity pages.
 * Records are stored under the key 'fitness_records' as a JSON array,
 * newest first.
 *
 * Record shape (all activity types share this):
 * {
 *   id:              string,          // unique id (timestamp-based)
 *   type:            string,          // 'hike' | 'run' | 'swim' | 'cycle' | 'gym' | 'strength'
 *   label:           string,          // Display label e.g. 'Hike'
 *   emoji:           string,          // e.g. '🥾'
 *   date:            string,          // 'Mar 15, 2026'
 *   dateISO:         string,          // '2026-03-15'
 *   duration:        string,          // '45 min' | '1 h 20 min'
 *   durationSeconds: number,          // raw elapsed seconds
 *   badge:           string,          // short name shown in Records list
 *   details:         Array<{icon, text}>,  // detail chips
 *   distanceKm:      number,          // 0 if not applicable
 *   // activity-specific raw fields stored alongside for future use
 * }
 *
 * To connect to a backend later:
 *   - Replace getAll()  with GET  /api/records
 *   - Replace add()     with POST /api/records
 *   - Replace remove()  with DELETE /api/records/:id
 */

var ActivityStore = (function () {
  var STORAGE_KEY = 'fitness_records';

  function read() {
    try {
      return JSON.parse(localStorage.getItem(STORAGE_KEY) || '[]');
    } catch (e) {
      return [];
    }
  }

  function write(records) {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(records));
  }

  return {
    /** Return all saved records (newest first). */
    getAll: function () {
      return read();
    },

    /** Prepend a new record and persist. Returns the record. */
    add: function (record) {
      var records = read();
      records.unshift(record);
      write(records);
      return record;
    },

    /** Remove a record by id. */
    remove: function (id) {
      write(read().filter(function (r) { return r.id !== id; }));
    },

    /** Clear all records (useful for dev/testing). */
    clear: function () {
      localStorage.removeItem(STORAGE_KEY);
    }
  };
})();
