(ns boost-bin.handler-test
  (:require [speclj.core :refer :all]
            [ring.mock.request :as mock]
            [boost-bin.handler :as underTest]
            [ring.adapter.jetty :refer [run-jetty]]))

(describe "The routes"
  (describe "main route"
    (let [response (underTest/app (mock/request :get "/"))]
      (it "should return a 200"
          (should= (:status response) 200))
      (it "should return Hello World"
          (should= (:body response) "Hello World"))))

  (describe "not-found route"
    (let [response (underTest/app (mock/request :get "/invalid"))]
      (it "should return a 400"
          (should= (:status response) 404)))))


(describe "The app"
  (it "should default to port 8080"
    (with-redefs [run-jetty (fn [handler opts] [handler opts])]
      (should= 8080 (->
											(underTest/-main)
											(nth 1)
											(:port)))))
	(it "should overide the port with first arg"
		(with-redefs [run-jetty (fn [handler opts] [handler opts])]
			(should= 8081 (->
											(underTest/-main "8081")
											(nth 1)
											(:port)))))
	(it "should set the app handler"
		(with-redefs [run-jetty (fn [handler opts] [handler opts])]
			(should= underTest/app (first (underTest/-main))))))
