(ns boost-bin.db)

(defn save-data-log
  [data]
  (slurp (:tempfile data)))