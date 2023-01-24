// @GENERATOR:play-routes-compiler
// @SOURCE:D:/IT_PRJ/CODE/ITSD-DT2022-Template/conf/routes
// @DATE:Mon Jan 23 17:34:31 GMT 2023


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
