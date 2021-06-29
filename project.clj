(defproject skattelisten-csv-parser "0.1.0-SNAPSHOT"
  :description "skattelisten csv to json parser"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [cheshire "5.10.0"]]
  :main ^:skip-aot skattelisten-csv-parser.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
