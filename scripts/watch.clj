(require '[cljs.build.api :as b])

(b/watch "src"
  {:main 'mies-blank.core
   :output-to "out/mies_blank.js"
   :output-dir "out"})
