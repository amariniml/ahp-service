package ahp.service

class PingController {

    def ping() {
        render(status: 200, text: 'pong')
    }
}

