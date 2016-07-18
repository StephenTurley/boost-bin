(ns boost-bin.csv
  (:require [clojure.data.csv :as c]))
(defn as-vector-map
  [csv-file]
	(let [csv (c/read-csv (slurp csv-file))]
		(zipmap (first csv)
						(apply map list (rest csv)))))