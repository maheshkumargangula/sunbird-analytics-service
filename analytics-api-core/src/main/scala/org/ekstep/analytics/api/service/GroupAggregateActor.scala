package org.ekstep.analytics.api.service

import akka.actor.Actor
import org.ekstep.analytics.api.util.CommonUtil
import org.ekstep.analytics.api.{APIIds, Response}

class GroupAggregateActor extends Actor {

    receive
    override def receive: PartialFunction[Any, Unit] = {
        case _ => activityAggregate
    }

    def activityAggregate(): Response = {
        CommonUtil.OK(APIIds.GROUP_ACTIVITY_AGG_REQUEST, Map())
    }
}
