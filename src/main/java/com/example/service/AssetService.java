package com.example.service;

import com.example.entity.Asset;
import com.example.repo.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssetService {
    @Autowired
    AssetRepository assetRepository;

    public Asset addNewAsset(Asset asset) {
        if(assetRepository != null) {
            return assetRepository.save(asset);
        }

        return null;
    }
}
