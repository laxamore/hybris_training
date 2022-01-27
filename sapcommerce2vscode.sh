#!/bin/bash

PROJECTS_DIR=($(find $HYBRIS_HOME_DIR/hybris/bin/ -name ".project" | sed -E "s|/.project||g"))
PROJECTS_DIR_J="{\"name\":\"config\",\"path\":\"$HYBRIS_HOME_DIR/hybris/config\"},"

for i in ${!PROJECTS_DIR[@]}; do
    PROJECT_DIR="${PROJECTS_DIR[$i]}"
    PROJECTS_DIR_J+="$(jq -c -n --arg PROJECT_DIR "$PROJECT_DIR" --arg PROJECT_NAME "$(echo $PROJECT_DIR | sed -E "s|$HYBRIS_HOME_DIR/hybris/bin/||g")" '{name: $PROJECT_NAME, path: $PROJECT_DIR}')"
    if (( $i != ${#PROJECTS_DIR[@]}-1 )); then
        PROJECTS_DIR_J+=","
    fi
done

# echo $PROJECTS_DIR_J
jq -n --argjson PROJECTS_DIR_J "[$PROJECTS_DIR_J]" '{folders: $PROJECTS_DIR_J}' > workspace.code-workspace