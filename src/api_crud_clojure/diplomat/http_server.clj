(ns api-crud-clojure.diplomat.http-server
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.body-params :as body-params]
            [io.pedestal.http.route :as route]
            [api-crud-clojure.diplomat.http-in.api-version :as http-in.api-version]
            [api-crud-clojure.diplomat.http-in.users :as http-in.users]
            [users.create :as users.create]
            [users.delete :as users.delete]
            [users.read :as users.read]
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
       :get users.read/read-users
       :route-name :get-all-users]
      ["/users"
       :post (conj common-interceptors users.create/create-users)
       :route-name :create-users]
      ["/users/:id"
       :delete users.delete/delete-users-by-id
       :route-name :delete-users]
      ["/users/:id"
       :get users.read/read-users-by-id
       :route-name :read-users-by-id]
      }))