package de.minersgames.melonigemelone.essentials.utils.model;

import java.util.UUID;

public class TpaModel {

    public enum Variant{
        TPA,
        TPAHERE
    }

    private UUID applicantUUID;
    private UUID targetUUID;
    private Variant variant;

    public TpaModel(UUID applicantUUID, UUID targetUUID, Variant variant){
        this.applicantUUID = applicantUUID;
        this.targetUUID = targetUUID;
        this.variant = variant;
    }

    public UUID getApplicantUUID() {return applicantUUID;}
    public UUID getTargetUUID() {return targetUUID;}
    public Variant getVariant() {return  variant;}
}
