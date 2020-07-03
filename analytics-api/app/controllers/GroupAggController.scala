package controllers

import akka.actor.{ActorRef, ActorSystem}
import akka.pattern.ask
import javax.inject.{Inject, Named}
import org.ekstep.analytics.api._
import org.ekstep.analytics.api.service._
import org.ekstep.analytics.api.util.JSONUtils
import play.api.Configuration
import play.api.libs.json.Json
import play.api.mvc.{Request, _}

import scala.concurrent.ExecutionContext

class GroupAggController @Inject()(
                                    @Named("group-agg-actor") groupAggActor: ActorRef,
                                    system: ActorSystem,
                                    configuration: Configuration,
                                    cc: ControllerComponents
                                  )(implicit ec: ExecutionContext)
  extends BaseController(cc, configuration)  {

    def activityAggregate() = Action.async { request: Request[AnyContent] =>
        val body: String = Json.stringify(request.body.asJson.get)
        val res = ask(groupAggActor, SubmitReportRequest(body, config)).mapTo[Response]
        res.map { x =>
            result(x.responseCode, JSONUtils.serialize(x))
        }
    }

}
