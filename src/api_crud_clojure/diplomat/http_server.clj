(ns api-crud-clojure.diplomat.http-server
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.body-params :as body-params]
            [io.pedestal.http.route :as route]
            [api-crud-clojure.diplomat.http-in.api-version :as http-in.api-version]
            [api-crud-clojure.diplomat.http-in.users :as http-in.users]
            [users.create :as users.create]
            [users.delete :as users.delete]
            ))

(def common-interceptors
  [(body-params/body-params)
   http/json-body])

(def routes
  (route/expand-routes
    #{["/api-version"
       :get http-in.api-version/api-version
       :route-name :api-version]
      ["/users"
       :get http-in.users/get-all-users
       :route-name :get-all-users]
      ["/users"
       :post (conj common-interceptors users.create/create-users)
       :route-name :create-users]
      ["/users"
       :delete users.delete/delete-user
       :route-name :delete-users]
      }))