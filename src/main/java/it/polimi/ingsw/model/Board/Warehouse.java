package it.polimi.ingsw.model.Board;

import it.polimi.ingsw.model.Board.Depot;
import it.polimi.ingsw.model.enumeration.ResourceType;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;

public class Warehouse {
    private int initialDepot = 3;
    private ArrayList<Depot> depotList;

    /**
     * Class constructor.
     */
    public Warehouse(){
        this.depotList = new ArrayList<>(initialDepot);
        int sizeD = initialDepot;

        for(int i=0; i<initialDepot; i++){
            depotList.add(i, new Depot(sizeD, 1));
            depotList.get(i).setResourceQuantity(0);
            depotList.get(i).setResourceType(ResourceType.EMPTY);
            sizeD--;
        }
    }

    public ArrayList<Depot> getDepotList(){
        return depotList;
    }

    public HashMap<ResourceType, Integer> getAllResources(){
        HashMap<ResourceType,Integer> resource=new HashMap<ResourceType, Integer>();
        for(int i=0; i<3;i++){
            resource.put(depotList.get(i).getResourceType(),depotList.get(i).getResourceQuantity());
        }
        for(int i=3;i<depotList.size();i++){
            if (resource.containsKey(depotList.get(i).getResourceType())){
                resource.put(depotList.get(i).getResourceType(), resource.get(depotList.get(i).getResourceType())+depotList.get(i).getResourceQuantity());
            }
            else  resource.put(depotList.get(i).getResourceType(),depotList.get(i).getResourceQuantity());

        }
       return resource;
    }


    public  void spendResources(HashMap<ResourceType, Integer> resources){
        for(int i=0; i<depotList.size(); i++){
            if(resources.containsKey(depotList.get(i).getResourceType())){
                if(resources.get(depotList.get(i).getResourceType())<=depotList.get(i).getResourceQuantity()){
                    depotList.get(i).setResourceQuantity(depotList.get(i).getResourceQuantity()-resources.get(depotList.get(i).getResourceType()));
                    resources.remove(depotList.get(i).getResourceType());
                    if( depotList.get(i).getResourceQuantity()==0){
                        depotList.get(i).setResourceType(ResourceType.EMPTY);
                    }
                }
                else{
                    resources.put(depotList.get(i).getResourceType(),resources.get(depotList.get(i).getResourceType())-depotList.get(i).getResourceQuantity() );
                    depotList.get(i).setResourceQuantity(0);
                    depotList.get(i).setResourceType(ResourceType.EMPTY);
                }
            }
        }
    }
    /**
     * This method adds a special depot.
     * @param resourceType type of resources in leader card' ability
     */
    public void addDepot(ResourceType resourceType){
        Depot depotLeader = new Depot(2, 0);
        depotLeader.setResourceType(resourceType);
        depotList.add(depotLeader);
    }
    public void replaceResources(HashMap<Integer, ResourceType> depotToResource, HashMap<Integer, Integer> depotToQuantity, ArrayList<Integer> resourceToLeader){
        for(int i=0; i<3; i++){
            depotList.get(i).replaceDepot(depotToResource.get(i), depotToQuantity.get(i));
        }
        for(int i=0;i<resourceToLeader.size();i++){
            depotList.get(i+3).setResourceQuantity(resourceToLeader.get(i));
        }
    }

    public HashMap<Integer,ResourceType> getDepotToResource(){
        HashMap<Integer,ResourceType> depotToResource=new HashMap<Integer, ResourceType>();
        for(int i=0; i<depotList.size();i++){
            depotToResource.put(i, depotList.get(i).getResourceType());
        }
        return depotToResource;
    }
    public HashMap<Integer,Integer> getDepotToQuantity(){
        HashMap<Integer,Integer> depotToQuantity=new HashMap<Integer, Integer>();
        for(int i=0; i<depotList.size();i++){
            depotToQuantity.put(i, depotList.get(i).getResourceQuantity());
        }
        return depotToQuantity;
    }
    public ArrayList<ResourceType> getLeaderDepot(){
        ArrayList<ResourceType> leaderDepot=new ArrayList<ResourceType>();
        for(int i=3; i<depotList.size();i++){
            leaderDepot.add(depotList.get(i).getResourceType());
        }
        return leaderDepot;
    }

    public HashMap<ResourceType, Integer> rearrange(int depot1, int depot2) throws InvalidParameterException{
        HashMap<ResourceType, Integer> discardingResources = new HashMap<>();

        //From depot2 to tempDepot
        if(getDepotList().get(depot1).getRearrangeble()==1 && getDepotList().get(depot2).getRearrangeble()==1) {
            Depot depot = new Depot(depotList.get(depot2).getSize(), depotList.get(depot2).getRearrangeble());
            depot.setResourceType(depotList.get(depot2).getResourceType());
            depot.setResourceQuantity(depotList.get(depot2).getResourceQuantity());

            // if depot1's quantity > depot2's size
            if(depotList.get(depot1).getResourceQuantity() > depotList.get(depot2).getSize()){
                discardingResources.put(depotList.get(depot1).getResourceType(),
                        depotList.get(depot1).getResourceQuantity() - depotList.get(depot2).getSize());
                depotList.get(depot2).setResourceQuantity(depotList.get(depot2).getSize());
            }
            // if depot2's quantity > depot1's size
            else if(depot.getResourceQuantity() > depotList.get(depot1).getSize()){
                discardingResources.put(depot.getResourceType(), depot.getResourceQuantity() - depotList.get(depot1).getSize());
                depotList.get(depot1).setResourceQuantity(depotList.get(depot1).getSize());
            }

            else {
                depotList.get(depot2).setResourceQuantity(depotList.get(depot1).getResourceQuantity());
                depotList.get(depot1).setResourceQuantity(depot.getResourceQuantity());
            }

            depotList.get(depot2).setResourceType(depotList.get(depot1).getResourceType());
            depotList.get(depot1).setResourceType(depot.getResourceType());

        } else throw new InvalidParameterException();
        return discardingResources;
    }

    /**
     *
     * @param resource
     * @param resourceQuantity
     * @param floor
     * @return
     */

    public int place(ResourceType resource, int resourceQuantity, int floor){
        int leftResources = resourceQuantity - getSpace().get(floor);
        depotList.get(floor).setResourceType(resource);
        if(leftResources>0) {
            depotList.get(floor).setResourceQuantity(depotList.get(floor).getSize());
            return leftResources;
        }
        else {
            depotList.get(floor).setResourceQuantity(resourceQuantity + depotList.get(floor).getResourceQuantity());
            return 0;
        }


    }



    /**
     * this method is used to know if there is resource in warehouse
     * @param res type of resource that are you looking for
     * @return i the depot that have res, -1 if the depot does not have the requested resource
     */
    public int hasResource(ResourceType res){
        for(int i=0; i<depotList.size(); i++){
            if(depotList.get(i).getResourceType().equals(res) && depotList.get(i).getResourceQuantity() == depotList.get(i).getSize())
                return i;
            else if(depotList.get(i).getResourceType() == res)
                return i;
        }
        return -1;
    }

    /**
     * this method is used to see if warehouse is empty
     * @return (true if warehouse is empty and false if it is not)
     */

    public Boolean isEmpty() {
        for(int i=0; i<depotList.size(); i++){
            if(depotList.get(i).getResourceQuantity() != 0 && depotList.get(i).getResourceType().getVal() != -1)
                return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    public ArrayList<Integer> getSpace(){
        ArrayList<Integer> space= new ArrayList<>();
        for(int i=0; i<depotList.size(); i++){
            space.add(i,depotList.get(i).getSize() - depotList.get(i).getResourceQuantity());
        }
        return space;
    }

    public ArrayList<Integer> availableDepot(ResourceType res){
        ArrayList<Integer> freeDepot = new ArrayList<>();
        for(int i=0; i< getDepotList().size(); i++){
            if(hasResource(res)==i || getSpace().get(i)==getDepotList().get(i).getSize()){
                freeDepot.add(i);
            }
        }
        return freeDepot;
    }

}
