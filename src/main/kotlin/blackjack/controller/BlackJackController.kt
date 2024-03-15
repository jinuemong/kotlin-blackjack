package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.GameManager
import blackjack.model.Participant
import blackjack.model.Participants
import blackjack.view.InputView
import blackjack.view.OutputView
import blackjack.view.user.UserDecision
import blackjack.view.user.UserInputValidator

class BlackJackController {
    private lateinit var gameManager: GameManager

    fun runBlackJackGame() {
        startGameFlow()
        playGame()
        showResult()
    }

    private fun startGameFlow() {
        val participants = makeParticipants()
        participants.players.forEach { player ->
            player.settleBettingMoney(InputView.inputPlayerMoney(player))
        }
        gameManager =
            GameManager(
                participants = participants,
            )
        gameManager.setGame()
        OutputView.outputParticipantsName(
            dealerName = participants.dealer.getName(),
            players = participants.players,
        )

        participants.dealer.openInitCards()
            .also { cards ->
                if (cards.isEmpty()) {
                    println(ERROR_CARD_INDEX)
                }
            }
            .forEach { firstCard ->
                OutputView.outputDealerCurrentHandCard(
                    name = participants.dealer.getName(),
                    firstCard = firstCard,
                )
            }
        OutputView.outputPlayersCurrentHandCard(participants.players)
    }

    private fun playGame() {
        gameManager.getAlivePlayers().forEach { participant ->
            playPlayer(participant)
        }
        playDealer()
    }

    private fun playPlayer(participant: Participant) {
        while (participant.checkShouldDrawCard()) {
            processPlayerDecision(participant)
        }
    }

    private fun processPlayerDecision(participant: Participant) {
        when (InputView.inputPlayerDecision(participant.getName())) {
            UserDecision.YES -> {
                processUserDecision(participant)
                OutputView.outputPlayerCurrentHandCard(participant)
            }

            UserDecision.NO -> participant.transitionToStayState()
        }
    }

    private fun playDealer() {
        val dealer = gameManager.getDealer()
        while (dealer.checkShouldDrawCard()) {
            OutputView.outputDealerRule()
            processUserDecision(dealer)
        }
    }

    private fun showResult() {
        OutputView.outputParticipantsHandCard(gameManager.getParticipants())
        OutputView.outputBlackResult()
        val gameResult = gameManager.calculateGameResult()
        OutputView.outputParticipantResult(
            dealer = gameManager.getDealer(),
            gameResult = gameResult,
        )
    }

    private fun processUserDecision(participant: Participant) {
        try {
            participant.draw(gameManager.returnCardForParticipant())
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }

    private fun makeParticipants(): Participants {
        val dealer = Dealer()
        val players = InputView.inputPlayers()
        return UserInputValidator.checkParticipants(dealer, players) ?: makeParticipants()
    }

    companion object {
        const val ERROR_CARD_INDEX = "딜러가 가지고 있는 카드가 없습니다."
    }
}
