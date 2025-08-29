(ns users.test
(require '[users.create :as uc])
(def fake-request
  {:json-params {:name "Gustavo"
                 :email "gustavo@example.com"}})
(uc/create-users fake-request)

