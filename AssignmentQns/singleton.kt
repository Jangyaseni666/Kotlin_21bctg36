object DatabaseConnection {
    private var isConnected = false

    fun connect() {
        if (!isConnected) {
            println("Connecting to database...")
            isConnected = true
            println("Connected to database")
        } else {
            println("Already connected to database")
        }
    }

    fun disconnect() {
        if (isConnected) {
            println("Disconnecting from database...")
            isConnected = false
            println("Disconnected from database")
        } else {
            println("Already disconnected from database")
        }
    }
}

fun main() {
    DatabaseConnection.connect()
    DatabaseConnection.disconnect()
}
