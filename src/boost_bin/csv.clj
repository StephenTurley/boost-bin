(ns boost-bin.csv
  (:require [clojure.data.csv :as c]
            [clojure.string :as s]))

(defn remove-special-chars-and-whitespace
  [string]
  (s/replace string #"[^a-z|^A-Z|^0-9]"  #(if (= % " ") "-" "" )))

(defn keywordize
  [key-strings]
  (map remove-special-chars-and-whitespace key-strings))

(defn as-vector-map
  [csv-file]
  (let [csv (c/read-csv (slurp csv-file))]
    (zipmap (keywordize (first csv))
            (apply map list (rest csv)))))
