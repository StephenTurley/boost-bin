(ns boost-bin.db
  (:require [boost-bin.csv :as csv]))

(defn save-data-log
  [data]
	(csv/as-vector-map (:tempfile data)))