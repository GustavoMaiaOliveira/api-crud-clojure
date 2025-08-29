(defproject api-crud-clojure "0.1.0-SNAPSHOT"
  :description "API Crud em Clojure, para fazer, ver, alterar e deletar cadastro"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [io.pedestal/pedestal.service "0.5.10"]
                 [io.pedestal/pedestal.route "0.5.10"]
                 [io.pedestal/pedestal.jetty "0.5.10"]
                 [cheshire "5.11.0"]
                 [org.clojure/data.json "2.5.0"]]
  :source-paths ["src"]
  :main ^:skip-aot api-crud-clojure.diplomat.http-client
  :target-path "target/%s"
  :profiles {:dev {:aliases {"run" ["trampoline" "run"]}
                   :dependencies [[ch.qos.logback/logback-classic "1.2.11"]]}})