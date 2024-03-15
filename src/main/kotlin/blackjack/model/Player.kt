package blackjack.model

class Player(userInformation: UserInformation) : Participant(userInformation) {
    override fun openInitCards(): List<Card> {
        return getCards().take(GameManager.INIT_HAND_CARD_COUNT)
    }

    override fun checkShouldDrawCard(): Boolean {
        return checkHitState()
    }

    companion object {
        const val ERROR_DUPLICATION_NAME = "사용자 이름은 중복이 불가능 합니다."
    }
}
