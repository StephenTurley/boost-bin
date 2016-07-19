(ns boost-bin.db
  (:require [boost-bin.csv :as csv]
						[monger.core :as mg]
						[monger.collection :as mc])
	(:import org.bson.types.ObjectId))

(defn save-data-log
  [data]
	(let [conn (mg/connect)
				db (mg/get-db conn "boostbin")
				id (ObjectId.)]
		(mc/insert-and-return db "datalogs"
							 {:id id, :data (csv/as-vector-map (:tempfile data))})
		{:id (.toString id)}))