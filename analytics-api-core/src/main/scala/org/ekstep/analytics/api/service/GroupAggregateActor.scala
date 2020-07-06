package org.ekstep.analytics.api.service

import akka.actor.Actor
import com.typesafe.config.Config
import org.ekstep.analytics.api.util.{CommonUtil, JSONUtils}
import org.ekstep.analytics.api.{APIIds, GroupActivityRequest, GroupActivityRequestBody, Response}

class GroupAggregateActor extends Actor {

    case class GroupActivityAggregate(message: String, config: Config)

    override def receive: PartialFunction[Any, Unit] = {
        case GroupActivityAggregate(message: String, config: Config) => sender() ! activityAggregate(message)(config)
    }

    def activityAggregate(message: String)(implicit config: Config): Response = {
        println("Working... Actor...")
        val body = JSONUtils.deserialize[GroupActivityRequestBody](message)
        val request = body.request
        CommonUtil.OK(APIIds.GROUP_ACTIVITY_AGG_REQUEST, getMockResponse(request))
    }

    private def getMockResponse(request: GroupActivityRequest): Map[String, AnyRef] = {
        val activityMap = Map[String, AnyRef]("id" -> "do_1126981011606323201176", "type" -> "Course", "metadata" -> Map[String, AnyRef](), "agg" -> List(Map[String, AnyRef]("metric" -> "progress", "value" -> 60.asInstanceOf[AnyRef], "lastUpdatedOn" -> "2020-07-02T19:03:54.035+0530")), "members" -> List(Map[String, AnyRef]("id" -> "8454cb21-3ce9-4e30-85b5-fade097880d8", "agg" -> List(Map[String, AnyRef]("metric" -> "progress", "value" -> 50.asInstanceOf[AnyRef], "lastUpdatedOn" -> "2020-07-02T19:03:54.035+0530")))))
        Map[String, AnyRef]("groupId" -> request.groupId, "activity" -> activityMap)
    }
}
