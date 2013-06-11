(defproject pe "1.0.0-SNAPSHOT"
  :description "Pr*j*ct E*l*r Solutions"
  :url "https://github.com/filippovitale/pe"
  :license {:name "The MIT License (MIT)"
            :url "http://opensource.org/licenses/mit-license.php"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [net.mikera/core.matrix "0.7.2"]
                 [quil "1.6.0"]
                 [org.clojure/math.combinatorics "0.0.4"]
                 [perforate "0.2.4"]]
  :plugins [[perforate "0.2.4"]]
  :profiles {:clj1.5.1 {:dependencies [[org.clojure/clojure "1.5.1"]]}}
  :perforate {:environments [{:name :default-1.5
                              :profiles [:clj1.5.1 ]
                              :namespaces [benchmarks.pep-xxx]}]}
  ;:jvm-opts ["-Xmx4g" "-Xincgc" "-XX:+UseConcMarkSweepGC" "-XX:+DisableExplicitGC"]
  :main pep-395.quil)
