(ns boost-bin.handler
	(:require [compojure.core :refer :all]
						[compojure.route :as route]
						[ring.adapter.jetty :refer :all]
						[ring.middleware.json :as middleware]
						[ring.util.response :refer [response redirect]]
						[ring.middleware.defaults :refer [wrap-defaults site-defaults]]
						[clojure.java.io :as io]
						[boost-bin.db :as db])
  (:gen-class))

(defroutes app-routes
  (GET "/" []
		(io/resource "public/index.html"))
 	(GET "/datalog/*" []
		(io/resource "public/chart.html"))
 	(GET "/api/datalog/:id" [id]
		(response (:data (db/fetch-data-log id))))
  (POST "/datalog" {params :params}
		(redirect (str "/datalog/#" (db/save-data-log (:data params)))))
 	(route/resources "/")
  (route/not-found "Not Found"))

(def app (-> app-routes
					 	(middleware/wrap-json-response)
						(wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))))

(defn -main
  [& args]
  (let [port (Integer/parseInt (or (first args) "8080"))]
    (run-jetty app {:port port})))
