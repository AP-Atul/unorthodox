package cult.unorthodox.ui.read.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cult.unorthodox.databinding.ItemDetailsBinding;
import cult.unorthodox.databinding.ItemPassageBinding;
import cult.unorthodox.models.Story;

public class PassageAdapter extends RecyclerView.Adapter<PassageAdapter.MyViewHolder> {
    private static final int ITEM_PASSAGE = 0;
    private static final int ITEM_DETAIL = 1;
    private final List<String> passages;
    private final Story story;

    public PassageAdapter(Story story, List<String> passages) {
        this.story = story;
        this.passages = passages;
    }

    @NonNull
    @Override
    public PassageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_PASSAGE) {
            ItemPassageBinding binding = ItemPassageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new MyViewHolder(binding.getRoot(), viewType);
        } else {
            ItemDetailsBinding binding = ItemDetailsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new MyViewHolder(binding.getRoot(), viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull PassageAdapter.MyViewHolder holder, int position) {
        if (getItemViewType(position) == ITEM_PASSAGE) {
            holder.passageBinding.tvPassage.setText(passages.get(position - 1).replace("\n", ""));
        } else {
            Picasso.get().load(story.getArt()).into(holder.detailsBinding.art);
            holder.detailsBinding.holyTitle.setText(story.getTitle());
            holder.detailsBinding.holySubTitle.setText(story.getSubtitle());
        }
    }

    @Override
    public int getItemCount() {
        return passages.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return ITEM_DETAIL;
        return ITEM_PASSAGE;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemPassageBinding passageBinding;
        private ItemDetailsBinding detailsBinding;

        public MyViewHolder(@NonNull View view, int viewType) {
            super(view);
            if (viewType == ITEM_DETAIL) {
                detailsBinding = ItemDetailsBinding.bind(view);
            } else {
                passageBinding = ItemPassageBinding.bind(view);
            }
        }
    }
}
