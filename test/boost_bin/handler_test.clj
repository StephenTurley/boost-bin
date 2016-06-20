(ns boost-bin.handler-test
  (:require [speclj.core :refer :all]
            [ring.mock.request :as mock]
            [boost-bin.handler :refer :all]))

(describe "The routes"
  (describe "main route"
    (let [response (app (mock/request :get "/"))]
      (it "should return a 200"
          (should= (:status response) 200))
      (it "should return Hello World"
          (should= (:body response) "Hello World"))))

  (describe "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (it "should return a 400"
          (should= (:status response) 404)))))
