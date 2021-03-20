(defproject skattelisten-csv-parser "0.1.0-SNAPSHOT"
  :description "skattelisten csv to json parser"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/data.json "1.1.0"]]
  :main skattelisten-csv-parser.core
  :repl-options {:init-ns skattelisten-csv-parser.core})
