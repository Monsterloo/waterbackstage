package com.hhh.fund.waterwx.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 排水口污染源信息实体类
 * @author 3hzxp
 *
 */
@Entity
@Table(name="outfall_polluate_resource")
@NamedQuery(name="OutfallPolluateResource.findAll", query="SELECT r FROM OutfallPolluateResource r")
public class OutfallPolluateResource {
	
	@Id
	@GeneratedValue(generator="idGenerator")
	@GenericGenerator(name="idGenerator", strategy="uuid")
	@Column(length=32)
    private String id;

	@Column(name="riverName")
    private String rivername;
	
	@Column(name="riverCode")
    private String rivercode;

	@Column(name="area")
    private String area;

	@Column(name="leftOrRightBank")
    private String leftorrightbank;

	@Column(name="outFallType")
    private String outfalltype;

	@Column(name="outFallCode")
    private String outfallcode;

	@Column(name="secondaryUnit")
    private String secondaryunit;

	@Column(name="streetName")
    private String streetname;

	@Column(name="streetManager")
    private String streetmanager;

	@Column(name="village")
    private String village;

	@Column(name="villageManager")
    private String villagemanager;
	
	@Column(name="position")
    private String position;
	
	@Column(name="coordinate")
    private String coordinate;
	
	@Column(name="outFallSize")
    private String outfallsize;
	
	@Column(name="outFallShape")
    private String outfallshape;
	
	@Column(name="pollDescription")
    private String polldescription;
	
	@Column(name="drainageTo")
    private String drainageTo;
	
	@Column(name="rectificationMeasures")
    private String rectificationmeasures;
	
	@Column(name="theRectificationResponsibilityUnit")
    private String therectificationresponsibilityunit;
	
	@Column(name="timeOfCompletion")
    private String timeofcompletion;
	
	@Column(name="remark")
    private String remark;
	
	@Column(name="createTime")
	private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }


    public String getOutfalltype() {
        return outfalltype;
    }

    public void setOutfalltype(String outfalltype) {
        this.outfalltype = outfalltype == null ? null : outfalltype.trim();
    }

    public String getOutfallcode() {
        return outfallcode;
    }

    public void setOutfallcode(String outfallcode) {
        this.outfallcode = outfallcode == null ? null : outfallcode.trim();
    }

    public String getSecondaryunit() {
        return secondaryunit;
    }

    public void setSecondaryunit(String secondaryunit) {
        this.secondaryunit = secondaryunit == null ? null : secondaryunit.trim();
    }

    public String getStreetname() {
        return streetname;
    }

    public void setStreetname(String streetname) {
        this.streetname = streetname == null ? null : streetname.trim();
    }

    public String getStreetmanager() {
        return streetmanager;
    }

    public void setStreetmanager(String streetmanager) {
        this.streetmanager = streetmanager == null ? null : streetmanager.trim();
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village == null ? null : village.trim();
    }

    public String getVillagemanager() {
        return villagemanager;
    }

    public void setVillagemanager(String villagemanager) {
        this.villagemanager = villagemanager == null ? null : villagemanager.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate == null ? null : coordinate.trim();
    }

    public String getOutfallsize() {
        return outfallsize;
    }

    public void setOutfallsize(String outfallsize) {
        this.outfallsize = outfallsize == null ? null : outfallsize.trim();
    }

    public String getOutfallshape() {
        return outfallshape;
    }

    public void setOutfallshape(String outfallshape) {
        this.outfallshape = outfallshape == null ? null : outfallshape.trim();
    }

    public String getPolldescription() {
        return polldescription;
    }

    public void setPolldescription(String polldescription) {
        this.polldescription = polldescription == null ? null : polldescription.trim();
    }

    public String getRectificationmeasures() {
        return rectificationmeasures;
    }

    public void setRectificationmeasures(String rectificationmeasures) {
        this.rectificationmeasures = rectificationmeasures == null ? null : rectificationmeasures.trim();
    }

    public String getTherectificationresponsibilityunit() {
        return therectificationresponsibilityunit;
    }

    public void setTherectificationresponsibilityunit(String therectificationresponsibilityunit) {
        this.therectificationresponsibilityunit = therectificationresponsibilityunit == null ? null : therectificationresponsibilityunit.trim();
    }

    public String getTimeofcompletion() {
        return timeofcompletion;
    }

    public void setTimeofcompletion(String timeofcompletion) {
        this.timeofcompletion = timeofcompletion == null ? null : timeofcompletion.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
    

	public String getDrainageTo() {
		return drainageTo;
	}

	public void setDrainageTo(String drainageTo) {
		this.drainageTo = drainageTo;
	}

	public String getRivername() {
		return rivername;
	}

	public void setRivername(String rivername) {
		this.rivername = rivername;
	}

	public String getLeftorrightbank() {
		return leftorrightbank;
	}

	public void setLeftorrightbank(String leftorrightbank) {
		this.leftorrightbank = leftorrightbank;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getRivercode() {
		return rivercode;
	}

	public void setRivercode(String rivercode) {
		this.rivercode = rivercode;
	}

	@Override
	public String toString() {
		return "OutfallPolluateResource [id=" + id + ", rivername=" + rivername + ", area=" + area
				+ ", leftorrightbank=" + leftorrightbank + ", outfalltype=" + outfalltype + ", outfallcode="
				+ outfallcode + ", secondaryunit=" + secondaryunit + ", streetname=" + streetname + ", streetmanager="
				+ streetmanager + ", village=" + village + ", villagemanager=" + villagemanager + ", position="
				+ position + ", coordinate=" + coordinate + ", outfallsize=" + outfallsize + ", outfallshape="
				+ outfallshape + ", polldescription=" + polldescription + ", drainageTo=" + drainageTo
				+ ", rectificationmeasures=" + rectificationmeasures + ", therectificationresponsibilityunit="
				+ therectificationresponsibilityunit + ", timeofcompletion=" + timeofcompletion + ", remark=" + remark
				+ "]";
	}
}