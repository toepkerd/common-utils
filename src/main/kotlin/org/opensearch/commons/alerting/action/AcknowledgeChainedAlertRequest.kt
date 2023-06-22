/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.commons.alerting.action

import org.opensearch.action.ActionRequest
import org.opensearch.action.ActionRequestValidationException
import org.opensearch.common.io.stream.StreamInput
import org.opensearch.common.io.stream.StreamOutput
import java.io.IOException
import java.util.Collections

/** Request DTO for acknowledging chained alerts generated by workflow.*/
class AcknowledgeChainedAlertRequest : ActionRequest {
    val workflowId: String
    val alertIds: List<String>

    constructor(
        workflowId: String,
        alertIds: List<String>,
    ) : super() {
        this.workflowId = workflowId
        this.alertIds = alertIds
    }

    @Throws(IOException::class)
    constructor(sin: StreamInput) : this(
        sin.readString(), // workflowId
        Collections.unmodifiableList(sin.readStringList()), // alertIds
    )

    override fun validate(): ActionRequestValidationException? {
        return null
    }

    @Throws(IOException::class)
    override fun writeTo(out: StreamOutput) {
        out.writeString(workflowId)
        out.writeStringCollection(alertIds)
    }
}