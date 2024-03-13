package blackjack.model

class Dealer(name: String = DEFAULT_DEALER_NAME) : Participant(name) {
    fun openFirstCard(): Card? {
        return getCards().firstOrNull()
    }

    fun checkShouldDealerDrawCard(): Boolean {
        return getBlackJackScore() <= MIN_HAND_CARD_SCORE
    }

    companion object {
        private const val DEFAULT_DEALER_NAME = "딜러"
        const val MIN_HAND_CARD_SCORE: Int = 16
    }
}
