/*
 * Copyright (C)2019-2020 TVUNetworks, All Rights Reserved.
 * This source code and any compilation or derivative thereof is the proprietary
 * information of TVUNetworks and is confidential in nature.
 * Under no circumstances is this software to be exposed to or placed
 * under an Open Source License of any type without the expressed written
 * permission of TVUNetworks.
 */
package com.tvu.Metadata_BE.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tvu.Metadata_BE.Model.Scene;

public interface SceneRepository extends JpaRepository<Scene, String>{

}
