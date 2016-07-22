(ns boost-bin.db
  (:require [boost-bin.csv :as csv]
						[monger.core :as mg]
						[monger.collection :as mc])
	(:import org.bson.types.ObjectId))

(defn connect
  []
  (let [uri (or (System/getenv "MONGODB_URI")
                "mongodb:// 127.0.0.1/boostbin")]
    (mg/connect-via-uri uri)))


(defn save-data-log
  [data]
	(let [{:keys [conn db]} (connect)
				id (ObjectId.)]
		(mc/insert-and-return db "datalogs"
							 {:_id id, :data (csv/as-vector-map (:tempfile data))})
		(.toString id)))

(defn fetch-data-log
	[id]
	(let [{:keys [conn db]} (connect)]
		(mc/find-map-by-id db "datalogs" (ObjectId. id))))