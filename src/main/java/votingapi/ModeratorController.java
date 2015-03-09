package votingapi;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Naresh on 3/6/2015.
 */
@RestController

public class ModeratorController {

    public final AtomicInteger id= new AtomicInteger(12345);
    public final AtomicInteger pid= new AtomicInteger(123456);
    HashMap<Integer,Moderator> hashMap= new HashMap<Integer,Moderator>();
    HashMap<String,Poll> pollhash= new HashMap<String,Poll>();
    List<Poll> pollList= new ArrayList<Poll>();

    @RequestMapping(value="/moderators",method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Moderator createModerator(@Valid @RequestBody Moderator moderator )
    {
        Moderator m= new Moderator(id.incrementAndGet(),moderator.getName(),moderator.getEmail(),moderator.getPassword(),new Date());
        hashMap.put(m.getId(),m);
        return m;
    }
    @RequestMapping(value="/moderators/{moderator_id}",method= RequestMethod.GET)
    public Moderator getModerator(@PathVariable("moderator_id") Integer Id )
    {
        Moderator m= hashMap.get(Id);
        return m;
    }
    @RequestMapping(value="/moderators/{moderator_id}",method= RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public Moderator updateModerator(@PathVariable("moderator_id") Integer Id,@Valid @RequestBody Moderator moderator )
    {
        Moderator m=hashMap.get(Id);
        Moderator mo=new Moderator(Id,m.getName(),moderator.getEmail(),moderator.getPassword(),m.created_at);
        hashMap.put(Id, mo);
        return mo;
    }
    @RequestMapping(value="/moderators/{moderator_id}/polls",method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Poll createPolls(@PathVariable("moderator_id") Integer Id,@Valid @RequestBody Poll polls){
        Moderator m=hashMap.get(Id);
        Integer results[]={500,600};
        Poll p =new Poll(Integer.toString(pid.getAndIncrement(),36),polls.getQuestion(),polls.getStarted_at(),polls.getExpired_at(),polls.getChoice(),results);
        if(m.getPolls()!=null)
        {
            pollList=m.getPolls();
        }
        pollList.add(p);
        Moderator mo=new Moderator(m.getId(),m.getName(),m.getEmail(),m.getPassword(),m.getCreated_at(),pollList);
        hashMap.put(mo.getId(),mo);
        pollhash.put(p.getId(), p);
        p.setResults(null);
        return p;
    }
    @RequestMapping(value="/moderators/{moderator_id}/polls/{poll_id}",method= RequestMethod.GET)
    public Poll getPollWithResult(@PathVariable("poll_id") String Id )
    {
        Poll polls= pollhash.get(Id);
        Integer results[]={500,600};
        Poll p=new Poll(Id,polls.getQuestion(),polls.getStarted_at(),polls.getExpired_at(),polls.getChoice(),results);
        pollhash.put(p.getId(), p);
        return p;
    }
    @RequestMapping(value="/polls/{poll_id}",method= RequestMethod.GET)
    public Poll getPollWithoutResult(@PathVariable("poll_id") String Id )
    {
        Poll polls= pollhash.get(Id);
        Poll p=new Poll(Id,polls.getQuestion(),polls.getStarted_at(),polls.getExpired_at(),polls.getChoice());
        return p;
    }
    @RequestMapping(value="/moderators/{moderator_id}/polls",method= RequestMethod.GET)
    public List<Poll> getAllPolls(@PathVariable("moderator_id") Integer Id )
    {
        Moderator m= hashMap.get(Id) ;
        return m.getPolls();
    }

    @RequestMapping(value="/moderators/{moderator_id}/polls/{poll_id}",method= RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePoll(@PathVariable("poll_id") String polls,@PathVariable("moderator_id") Integer Id )
    {
        Moderator m=hashMap.get(Id);
        hashMap.remove(Id);
        if(m.getPolls()!=null){
        pollList=m.getPolls();
            Poll p;

            for (int i = 0; i < pollList.size(); i++) {

                    p = pollList.get(i);
                    if (p.getId().equals(polls)) {
                        pollList.remove(i);
                        break;
                    }
                }


        }
        Moderator mo = new Moderator(Id, m.getName(), m.getEmail(), m.getPassword(), m.getCreated_at(), pollList);
        hashMap.put(Id, mo);
        pollhash.remove(polls);

    }




    @RequestMapping(value="/polls/{poll_id}",method= RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void votePoll(@PathVariable("poll_id") String polls,@Valid @RequestParam(value="choice") Integer Id ) {
        Poll p=pollhash.get(polls);
        Integer results[]=p.getResults();
        results[Id]=results[Id]+1;
        Poll po=new Poll(p.getId(),p.getQuestion(),p.getStarted_at(),p.getExpired_at(),p.getChoice(),results);
        pollhash.put(po.getId(),po);

    }


}
