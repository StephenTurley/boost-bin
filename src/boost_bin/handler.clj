(ns boost-bin.handler
	(:require [compojure.core :refer :all]
						[compojure.route :as route]
						[ring.adapter.jetty :refer :all]
						[ring.middleware.defaults :refer [wrap-defaults site-defaults]]
						[clojure.java.io :as io])
  (:gen-class))

(defn saveDataLog
	[data]
	(slurp (:tempfile data)))

(defroutes app-routes
  (GET "/" [] (io/resource "public/index.html"))
  (POST "/datalog" {params :params}
		(saveDataLog (:data params)))
  (route/not-found "Not Found"))

(def app (wrap-defaults app-routes
												(assoc-in site-defaults [:security :anti-forgery] false)))

(defn -main
  [& args]
  (let [port (Integer/parseInt (or (first args) "8080"))]
    (run-jetty
      app
      {:port port})))
