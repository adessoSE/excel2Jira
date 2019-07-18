package de.adesso.excel2jira.jira;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import de.adesso.excel2jira.jira.domain.JiraIssue;

import java.io.IOException;

public class JiraIssueJsonSerializer extends JsonSerializer<JiraIssue> {

    @Override
    public void serialize(JiraIssue value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();

        gen.writeFieldName("update");
        gen.writeStartObject();
        gen.writeEndObject();

        gen.writeFieldName("fields");
        gen.writeStartObject();

        gen.writeFieldName("project");
        gen.writeStartObject();
        gen.writeStringField("id", String.valueOf(value.getProjectId()));
        gen.writeEndObject();

        gen.writeStringField("summary", value.getSummary());

        gen.writeFieldName("issuetype");
        gen.writeStartObject();
        gen.writeStringField("id", String.valueOf(value.getIssueType()));
        gen.writeEndObject();

        if(value.getAssignee() != null) {
            gen.writeFieldName("assignee");
            gen.writeStartObject();
            gen.writeStringField("name", value.getAssignee());
            gen.writeEndObject();
        }

        gen.writeFieldName("priority");
        gen.writeStartObject();
        gen.writeStringField("id", String.valueOf(value.getPriority()));
        gen.writeEndObject();

        //customfield_10321
        gen.writeObjectField("customfield_10321", value.getLabels());
        //gen.writeObjectField("labels", value.getLabels());

        gen.writeFieldName("fixVersions");
        gen.writeStartArray();
        for(Long fixVersion : value.getVersions()){
            gen.writeStartObject();
            gen.writeStringField("id", String.valueOf(fixVersion));
            gen.writeEndObject();
        }
        gen.writeEndArray();

        gen.writeEndObject();
        gen.writeEndObject();
    }
}