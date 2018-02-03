package integration

import com.stevebarreira.codename.model.GameStatus
import com.stevebarreira.codename.model.Games
import org.apache.http.HttpStatus
import org.apache.http.client.utils.URIBuilder
import org.springframework.context.annotation.Profile
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import spock.lang.Ignore
import spock.lang.Specification

@Ignore
class End2EndSpec extends Specification {

    static String appHost
    static int appPort

    RestTemplate restTemplate = new RestTemplate()

    def setupSpec(){
        appPort = System.getenv('app.port') ? Integer.parseInt(System.getenv('app.port')) : 8080
        appHost = System.getenv('app.host') ?: 'localhost'
    }

    def 'Test Rest Call Against API' (){
        when: 'Creating The Game'
        URI uriPost = buildGameURI()
        def response = restTemplate.postForEntity(uriPost, null, Games.class)

        then:
        response
        response.body
        response.body.id

        when: 'Reading the Game'
        URI uriGet  = buildGameURI(response.body.id)
        def getResponse = restTemplate.getForEntity(uriGet, Games.class)

        then:
        getResponse
        getResponse.body.id

        when: 'Updating the Game'
        getResponse.body.status = GameStatus.INACTIVE
        restTemplate.put(uriGet, getResponse.body)
        def secondGetResponse = restTemplate.getForEntity(uriGet, Games.class)

        then:
        secondGetResponse
        secondGetResponse.body.status == GameStatus.INACTIVE.status

        when: 'Deleting the Game'
        def deleteResponse = restTemplate.delete(uriGet)

        then:
        notThrown(RestClientException)

        when: 'Re Reading Game'
        def secondReadResponse = restTemplate.getForEntity(uriGet, Games.class)

        then:
        def httpExecpt = thrown(HttpClientErrorException)
        httpExecpt.statusCode.value() == HttpStatus.SC_NOT_FOUND
    }

    private URI buildGameURI(String gameId = ''){
        return new URIBuilder()
                .setHost(appHost)
                .setPort(appPort)
                .setScheme('HTTP')
                .setPath('/api/games/' + gameId)
                .build()
    }

}