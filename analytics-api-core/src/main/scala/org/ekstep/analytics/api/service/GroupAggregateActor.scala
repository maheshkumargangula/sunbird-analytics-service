package org.ekstep.analytics.api.service

import akka.actor.Actor
import org.ekstep.analytics.api.util.CommonUtil
import org.ekstep.analytics.api.{APIIds, Response}

class GroupAggregateActor extends Actor {

    override def receive: PartialFunction[Any, Unit] = {
        case _ => sender() ! activityAggregate
    }

    def activityAggregate(): Response = {
        println("Working... Actor...")
        CommonUtil.OK(APIIds.GROUP_ACTIVITY_AGG_REQUEST, Map())
    }
}
