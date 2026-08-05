package com.example.listview.mapper

import com.example.listview.model.CastMember
import com.example.listview.model.CastMemberResponse
import com.example.listview.model.ImageSize
import com.example.listview.network.IMAGE_BASE_URL


fun CastMemberResponse.toModel()  = CastMember(
    id = this.id,
    name = this.name,
    mainRole = this.department,
    character = this.character,
    profileUrl = "${IMAGE_BASE_URL}/${ImageSize.X_SMALL.size}/${this.profilePath}",
)