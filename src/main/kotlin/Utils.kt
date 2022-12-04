
fun readResource(resourceName: String) = {}.javaClass.getResourceAsStream("$resourceName.txt")?.bufferedReader()?.readLines()

