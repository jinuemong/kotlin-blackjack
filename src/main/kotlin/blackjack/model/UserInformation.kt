package blackjack.model

data class UserInformation(val name: String) {
    private var balance: Int = 0

    fun setMoney(money: Int) {
        balance = money
    }

    fun getBalance(): Int {
        return balance
    }
}