(ns boost-bin.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.adapter.jetty :refer :all]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]])
  (:gen-class))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (route/not-found "Not Found"))

(def app (wrap-defaults app-routes site-defaults))

(defn -main
  [& args]
  (run-jetty
    app
    {:port 8080}))
