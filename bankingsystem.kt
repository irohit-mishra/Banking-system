fun main() {
    println("Welcome to your banking system.")

    // Get user input for account type
    println("What type of account would you like to create?")
    println("1. Debit account")
    println("2. Credit account")
    println("3. Checking account")

    var accountType = ""
    var userChoice = 0

    while (accountType == "") {
        println("Choose an option (1, 2, or 3): ")
        userChoice = readLine()?.toIntOrNull() ?: 0

        accountType = when (userChoice) {
            1 -> "debit"
            2 -> "credit"
            3 -> "checking"
            else -> {
                println("Invalid choice. Please choose 1, 2, or 3.")
                ""
            }
        }
    }

    println("You have created a $accountType account.")

    var accountBalance = (0..1000).random()
    println("The current balance is $accountBalance dollars.")

    var isSystemOpen = true
    var option = 0

    while (isSystemOpen) {
        println("What would you like to do?")
        println("1. Check bank account balance")
        println("2. Withdraw money")
        println("3. Deposit money")
        println("4. Close the system")
        print("Enter your choice (1, 2, 3, or 4): ")

        option = readLine()?.toIntOrNull() ?: 0

        when (option) {
            1 -> println("The current balance is $accountBalance dollars.")
            2 -> performTransaction(accountType, "withdraw")
            3 -> performTransaction(accountType, "deposit")
            4 -> {
                isSystemOpen = false
                println("The system is closed.")
            }
            else -> println("Invalid choice. Please choose 1, 2, 3, or 4.")
        }
    }
}

fun performTransaction(accountType: String, mode: String) {
    print("Enter the amount you want to $mode: ")
    val amount = readLine()?.toIntOrNull() ?: 0

    when (mode) {
        "withdraw" -> {
            if (accountType == "debit") {
                debitWithdraw(amount)
            } else {
                withdraw(amount)
            }
        }
        "deposit" -> {
            if (accountType == "credit") {
                creditDeposit(amount)
            } else {
                deposit(amount)
            }
        }
    }
}

var accountBalance = 0

fun withdraw(amount: Int) {
    if (amount > accountBalance) {
        println("Not enough money in the account! The current balance is $accountBalance dollars.")
    } else {
        accountBalance -= amount
        println("You successfully withdrew $amount dollars. The current balance is $accountBalance dollars.")
    }
}

fun deposit(amount: Int) {
    accountBalance += amount
    println("You successfully deposited $amount dollars. The current balance is $accountBalance dollars.")
}

fun debitWithdraw(amount: Int) {
    if (amount > accountBalance) {
        println("Not enough money in the account! The current balance is $accountBalance dollars.")
    } else {
        withdraw(amount)
    }
}

fun creditDeposit(amount: Int) {
    if (accountBalance + amount > 0) {
        println("Deposit failed, you tried to pay off an amount greater than the credit balance. The checking balance is $accountBalance dollars.")
    } else if (amount == -accountBalance) {
        accountBalance = 0
        println("You have paid off this account!")
    } else {
        deposit(amount)
    }
}
